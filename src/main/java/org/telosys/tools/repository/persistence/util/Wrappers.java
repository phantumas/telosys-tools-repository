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
package org.telosys.tools.repository.persistence.util;

import org.telosys.tools.repository.persistence.wrapper.BaseWrapper;
import org.telosys.tools.repository.persistence.wrapper.ColumnWrapper;
import org.telosys.tools.repository.persistence.wrapper.EntityWrapper;
import org.telosys.tools.repository.persistence.wrapper.ForeignKeyColumnWrapper;
import org.telosys.tools.repository.persistence.wrapper.ForeignKeyWrapper;
import org.telosys.tools.repository.persistence.wrapper.GeneratedValueWrapper;
import org.telosys.tools.repository.persistence.wrapper.InverseJoinColumnsWrapper;
import org.telosys.tools.repository.persistence.wrapper.JoinColumnWrapper;
import org.telosys.tools.repository.persistence.wrapper.JoinColumnsWrapper;
import org.telosys.tools.repository.persistence.wrapper.JoinTableWrapper;
import org.telosys.tools.repository.persistence.wrapper.LinkWrapper;
import org.telosys.tools.repository.persistence.wrapper.SequenceGeneratorWrapper;
import org.telosys.tools.repository.persistence.wrapper.TableGeneratorWrapper;

public class Wrappers {

	//--------------------------------------------------------------------------------------------------
	// WRAPPERS 
	//--------------------------------------------------------------------------------------------------
	public final static BaseWrapper               BASE_WRAPPER = new BaseWrapper();

	public final static EntityWrapper             ENTITY_WRAPPER = new EntityWrapper();
	public final static ColumnWrapper             COLUMN_WRAPPER = new ColumnWrapper();
	public final static ForeignKeyWrapper         FOREIGNKEY_WRAPPER = new ForeignKeyWrapper();
	public final static ForeignKeyColumnWrapper   FOREIGNKEY_COLUMN_WRAPPER = new ForeignKeyColumnWrapper();

	public final static LinkWrapper               LINK_WRAPPER = new LinkWrapper();
	public final static JoinTableWrapper          JOIN_TABLE_WRAPPER  = new JoinTableWrapper();
	public final static JoinColumnWrapper         JOIN_COLUMN_WRAPPER          = new JoinColumnWrapper();	
	public final static JoinColumnsWrapper        JOIN_COLUMNS_WRAPPER         = new JoinColumnsWrapper();
	public final static InverseJoinColumnsWrapper INVERSE_JOIN_COLUMNS_WRAPPER = new InverseJoinColumnsWrapper();

	public final static GeneratedValueWrapper     GENERATED_VALUE_WRAPPER = new GeneratedValueWrapper();
	public final static SequenceGeneratorWrapper  SEQUENCE_GENERATOR_WRAPPER = new SequenceGeneratorWrapper();
	public final static TableGeneratorWrapper     TABLE_GENERATOR_WRAPPER = new TableGeneratorWrapper();
	
	//--------------------------------------------------------------------------------------------------

}
