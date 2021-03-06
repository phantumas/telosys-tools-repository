/**
 *  Copyright (C) 2008-2015  Telosys project org. ( http://www.telosys.org/ )
 *
 *  Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *          http://www.gnu.org/licenses/lgpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.telosys.tools.repository;

import java.util.List;

import org.telosys.tools.generic.model.Entity;
import org.telosys.tools.generic.model.ForeignKey;
import org.telosys.tools.generic.model.ForeignKeyColumn;
import org.telosys.tools.repository.model.AttributeInDbModel;
import org.telosys.tools.repository.model.EntityInDbModel;
import org.telosys.tools.repository.model.RepositoryModel;


/**
 * Utility class providing the unique method to set the Foreign Key type <br>
 * for each attribute involved in a Foreign Key ( Simple or Composite FK ) <br>
 *  
 * @author Laurent GUERIN
 *
 */
public class ForeignKeyTypeManager 
{
	private final static int FK_SIMPLE    = 1 ;
	private final static int FK_COMPOSITE = 2 ;
	
//	private void addForeignKeyParts( EntityInDbModel entity, DatabaseTable dbTable) {
//		//--- For each foreign key of the table ...
//		for ( DatabaseForeignKey dbFK : dbTable.getForeignKeys() ) {
//			// Build de FK instance
//			ForeignKeyInDbModel fk = buildForeignKey( dbFK ) ;
//			// Attach the FK to the entity
//			entity.storeForeignKey(fk);
//			
//			// Set FK type for each attribute involved in a FK  
//			setAttributesFKType(entity, fk); // Added in v 3.0.0
//		}
//	}
	
	/**
	 * Set the Foreign Key type for each attribute involved in a Foreign Key <br>
	 * NB : This method must be called before the links generation<br>
	 * ( it can be called many times on the same model ) 
	 * 
	 * @param repositoryModel
	 * @since v 3.0.0
	 */
	public void setAttributesForeignKeyInformation(RepositoryModel repositoryModel) {
		
		for ( Entity entity : repositoryModel.getEntities() ) {
			List<ForeignKey> foreignKeys = entity.getDatabaseForeignKeys();
			for ( ForeignKey fk : foreignKeys ) {
				EntityInDbModel referencedEntity = repositoryModel.getEntityByTableName( fk.getReferencedTableName() );
				// Set FK type for each attribute involved in a FK  
				setAttributesFKInfo((EntityInDbModel)entity, fk, referencedEntity); 
			}
		}		
	}
	
	/**
	 * Set the FK information for all the attributes associated with the given FK
	 * @param entity
	 * @param fk
	 * @param referencedEntity
	 * @since v 3.0.0
	 */
	private void setAttributesFKInfo(EntityInDbModel entity, ForeignKey fk, EntityInDbModel referencedEntity ) {
		List<ForeignKeyColumn> fkColumns = fk.getColumns() ;
		if ( fkColumns != null ) {
			if ( fkColumns.size() > 1 ) {
				//--- Composite FK ( many columns )
				for ( ForeignKeyColumn fkCol : fkColumns ) {
					setAttributeFKInfo(entity, fkCol, FK_COMPOSITE, referencedEntity) ;
				}
			}
			else if ( fk.getColumns().size() == 1 ) {
				//--- Simple FK ( only one column )
				ForeignKeyColumn fkCol = fkColumns.get(0);
				setAttributeFKInfo(entity, fkCol, FK_SIMPLE, referencedEntity) ;
			}
		}
	}
	
	/**
	 * Set the FK information for the attribute associated with the given FK Column
	 * @param entity
	 * @param fkCol
	 * @param fkType
	 * @param referencedEntity
	 * @since v 3.0.0
	 */
	private void setAttributeFKInfo(EntityInDbModel entity, ForeignKeyColumn fkCol, int fkType, EntityInDbModel referencedEntity ) {
		String fkColName = fkCol.getColumnName();
		AttributeInDbModel attribute = entity.getAttributeByColumnName(fkColName);
		if ( attribute != null ) {
			if ( fkType == FK_SIMPLE ) {
				attribute.setFKSimple(true);
				// Always set the "FK Simple" reference as the main referenced entity (priority = 1)
				attribute.setReferencedEntityClassName(referencedEntity.getClassName());
			}
			else if ( fkType == FK_COMPOSITE ) {
				attribute.setFKComposite(true);
				// Set the reference only if there's no "FK Simple" reference (priority = 2)
				if ( ! attribute.isFKSimple() ) {
					attribute.setReferencedEntityClassName(referencedEntity.getClassName());
				}
			}
		}
		else {
			throw new IllegalStateException("Cannot get attribute by column name'" + fkColName + "'");
		}
	}

}
