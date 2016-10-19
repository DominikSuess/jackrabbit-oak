/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.jackrabbit.oak.plugins.document.bundlor;

public interface Matcher {
    Matcher FAILED = new Matcher() {
        @Override
        public Matcher next(String name) {
            return FAILED;
        }

        @Override
        public boolean isMatch() {
            return false;
        }

        @Override
        public String getMatchedPath() {
            throw new IllegalStateException("No matching path for non matching matcher");
        }

        @Override
        public int depth() {
            return 0;
        }
    };

    /**
     * Returns a matcher for given child node name based on current state
     *
     * @param name child node name
     * @return child matcher
     */
    Matcher next(String name);

    /**
     * Returns true if there was a match wrt current child node path
     */
    boolean isMatch();

    /**
     * Relative node path from the bundling root if
     * there was a match
     */
    String getMatchedPath();

    /**
     * Matcher depth. For match done for 'x/y' depth is 2
     */
    int depth();
}
