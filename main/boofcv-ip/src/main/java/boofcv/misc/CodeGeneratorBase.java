/*
 * Copyright (c) 2011-2017, Peter Abeles. All Rights Reserved.
 *
 * This file is part of BoofCV (http://boofcv.org).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package boofcv.misc;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;


/**
 * <p>Base class for code generators.</p>
 *
 * @author Peter Abeles
 */
public abstract class CodeGeneratorBase {

	protected PrintStream out;
	protected String className;

	public CodeGeneratorBase(boolean useDefaultName) {
		if( useDefaultName ) {
			autoSelectName();
		}
	}

	public CodeGeneratorBase() {
		autoSelectName();
	}

	public void autoSelectName() {
		className = getClass().getSimpleName();
		if( className.startsWith("Generate") ) {
			int l = new String("Generate").length();
			className = className.substring(l, className.length());
			try {
				initFile();
			} catch( FileNotFoundException e ) {
				throw new RuntimeException(e);
			}
		} else {
			System.out.println("Class name doesn't start with Generate");
		}
	}

	/**
	 * Creates 
	 *
	 * @throws FileNotFoundException
	 */
	public abstract void generate() throws FileNotFoundException;

	public void initFile() throws FileNotFoundException {
		out = new PrintStream(new FileOutputStream(className + ".java"));
		out.print(CodeGeneratorUtil.copyright);
		out.println();
		out.println("package " + getPackage() + ";");
		out.println();
	}

	public void setOutputFile( String className ) throws FileNotFoundException {
		if( this.className != null )
			throw new IllegalArgumentException("ClassName already set.  Out of date code?");
		this.className = className;
		initFile();
	}

	public String generatedString() {
		return "@Generated(\""+getClass().getName()+"\")\n";
	}

	public String getPackage() {
		return getClass().getPackage().getName();
	}
}
