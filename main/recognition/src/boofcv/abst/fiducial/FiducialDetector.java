/*
 * Copyright (c) 2011-2014, Peter Abeles. All Rights Reserved.
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

package boofcv.abst.fiducial;

import boofcv.struct.calib.IntrinsicParameters;
import boofcv.struct.image.ImageBase;
import boofcv.struct.image.ImageType;
import georegression.struct.se.Se3_F64;

/**
 * Interface for detecting fiducials.  If the {@link boofcv.struct.calib.IntrinsicParameters} specifies lens
 * distortion then it will be automatically removed and if there is no lens distortion then it will skip that step.
 *
 *
 * @author Peter Abeles
 */
// TODO Comment
public interface FiducialDetector<T extends ImageBase>
{
	public void detect( T input );

	public void setIntrinsic( IntrinsicParameters intrinsic );

	public int totalFound();

	public void getFiducialToWorld(int which, Se3_F64 fiducialToSensor );

	public int getId( int which );

	public ImageType<T> getInputType();
}