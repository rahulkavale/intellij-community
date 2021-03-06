/*
 * Copyright 2000-2009 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * User: anna
 * Date: 13-Jul-2007
 */
package org.jetbrains.idea.maven.wizards;

import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.projectImport.ProjectOpenProcessorBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.idea.maven.model.MavenConstants;
import org.jetbrains.idea.maven.project.MavenProject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MavenProjectOpenProcessor extends ProjectOpenProcessorBase<MavenProjectBuilder> {

  private static final Logger LOG = Logger.getInstance(MavenProjectOpenProcessor.class);

  public static final String[] POM_XML = new String[]{MavenConstants.POM_XML};

  public MavenProjectOpenProcessor(@NotNull MavenProjectBuilder builder) {
    super(builder);
  }

  @Nullable
  public String[] getSupportedExtensions() {
    return POM_XML;
  }

  @Override
  public boolean canOpenProject(VirtualFile file) {
    if (file.isDirectory()) {
      long startTime = System.currentTimeMillis();

      try {
        return file.findChild(MavenConstants.POM_XML) != null;
      }
      finally {
        long now = System.currentTimeMillis();

        int limit = ApplicationManager.getApplication().isInternal() ? 100 : 500;

        if (now - startTime > limit) {
          LOG.error("Finding pom.xml got " + (now - startTime) + "ms. See IDEA-71265. " + file.getPath(), new Exception());
        }
      }
    }
    else {
      if (MavenConstants.POM_XML.equals(file.getName())) {
        return true;
      }
    }

    return false;
  }

  public boolean doQuickImport(VirtualFile file, WizardContext wizardContext) {
    getBuilder().setFiles(Arrays.asList(file));

    if (!getBuilder().setSelectedProfiles(new ArrayList<String>())) return false;

    List<MavenProject> projects = getBuilder().getList();
    if (projects.size() != 1) return false;

    getBuilder().setList(projects);
    wizardContext.setProjectName(getBuilder().getSuggestedProjectName());

    return true;
  }
}
