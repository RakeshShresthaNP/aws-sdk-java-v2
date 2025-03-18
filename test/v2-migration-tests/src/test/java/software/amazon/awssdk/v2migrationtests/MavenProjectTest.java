/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package software.amazon.awssdk.v2migrationtests;

import static software.amazon.awssdk.v2migrationtests.TestUtils.replaceVersion;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIf;

public class MavenProjectTest extends MavenTestBase {

    @BeforeAll
    static void setUp() throws IOException {
        mavenBefore = new File(MavenProjectTest.class.getResource("maven/before").getFile()).toPath();
        mavenAfter = new File(MavenProjectTest.class.getResource("maven/after").getFile()).toPath();
        target = new File(MavenProjectTest.class.getResource("/").getFile()).toPath().getParent();

        mavenActual = target.resolve("maven/actual");
        mavenExpected = target.resolve("maven/expected");

        deleteTempDirectories();

        FileUtils.copyDirectory(mavenBefore.toFile(), mavenActual.toFile());
        FileUtils.copyDirectory(mavenAfter.toFile(), mavenExpected.toFile());

        replaceVersion(mavenExpected.resolve("pom.xml"), sdkVersion);
        replaceVersion(mavenActual.resolve("pom.xml"), sdkVersion);
    }

    @Test
    @EnabledIf("versionAvailable")
    void mavenProject_shouldConvert() throws IOException {
        boolean experimental = false;
        verifyTransformation(experimental);
        verifyCompilation();
    }
}
