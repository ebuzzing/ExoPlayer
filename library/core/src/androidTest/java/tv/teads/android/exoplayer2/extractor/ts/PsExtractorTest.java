/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tv.teads.android.exoplayer2.extractor.ts;

import android.test.InstrumentationTestCase;
import tv.teads.android.exoplayer2.extractor.Extractor;
import tv.teads.android.exoplayer2.testutil.TestUtil;

/**
 * Unit test for {@link PsExtractor}.
 */
public final class PsExtractorTest extends InstrumentationTestCase {

  public void testSample() throws Exception {
    TestUtil.assertOutput(new TestUtil.ExtractorFactory() {
      @Override
      public Extractor create() {
        return new PsExtractor();
      }
    }, "ts/sample.ps", getInstrumentation());
  }

}
