/*
 * Copyright (c) 2011-2012, Peter Abeles. All Rights Reserved.
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

package boofcv.abst.feature.describe;

import boofcv.alg.feature.describe.DescribePointPixelRegion;
import boofcv.struct.feature.TupleDesc;
import boofcv.struct.feature.TupleDesc_F32;
import boofcv.struct.feature.TupleDesc_U8;
import boofcv.struct.image.ImageSingleBand;

/**
 * Wrapper around {@link boofcv.alg.feature.describe.DescribePointPixelRegion} for
 * {@link DescribeRegionPoint}.
 *
 * @author Peter Abeles
 */
public class WrapDescribePixelRegion<T extends ImageSingleBand, D extends TupleDesc>
		implements DescribeRegionPoint<T,D>
{
	DescribePointPixelRegion<T,D> alg;

	public WrapDescribePixelRegion(DescribePointPixelRegion<T, D> alg) {
		this.alg = alg;
	}

	private D createDescriptor() {
		if( alg.getDescriptorType() == TupleDesc_F32.class ) {
			return (D)new TupleDesc_F32(alg.getDescriptorLength());
		} else {
			return (D)new TupleDesc_U8(alg.getDescriptorLength());
		}
	}

	@Override
	public void setImage(T image) {
		alg.setImage(image);
	}

	@Override
	public int getDescriptionLength() {
		return alg.getDescriptorLength();
	}

	@Override
	public int getCanonicalRadius() {
		return alg.getDescriptorRadius();
	}

	@Override
	public D process(double x, double y, double orientation,
								 double scale, D ret)
	{
		if( ret == null ) {
			ret = createDescriptor();
		}

		alg.process((int)x,(int)y,ret);

		return ret;
	}

	@Override
	public boolean requiresScale() {
		return false;
	}

	@Override
	public boolean requiresOrientation() {
		return false;
	}

	@Override
	public Class<D> getDescriptorType() {
		return alg.getDescriptorType();
	}
}
