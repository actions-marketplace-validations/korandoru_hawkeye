/*
 * Copyright 2023 Korandoru Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.korandoru.hawkeye.core;

import io.korandoru.hawkeye.core.document.Document;
import io.korandoru.hawkeye.core.header.Header;

public class LicenseFormatter extends LicenseProcessor {

    public LicenseFormatter(HawkEyeConfig config) {
        super(config, Report.Action.FORMAT);
    }

    @Override
    protected void onHeaderNotFound(Document document, Header header, Report report) {
        if (document.headerDetected()) {
            document.removeHeader();
            report.add(document.getFile(), Report.Result.REPLACED);
        } else {
            report.add(document.getFile(), Report.Result.ADDED);
        }
        document.updateHeader(header);
        document.save();
    }

    @Override
    protected void onExistingHeader(Document document, Header header, Report report) {
        report.add(document.getFile(), Report.Result.NOOP);
    }
}
