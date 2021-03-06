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
package com.intellij.ide.commander;

import com.intellij.icons.AllIcons;
import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.util.treeView.AbstractTreeNode;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.Collection;
import java.util.Collections;

/**
 * @author Eugene Zhuravlev
 *         Date: Jun 23, 2005
 */
public class TopLevelNode extends AbstractTreeNode {
  private static final Icon ourUpLevelIcon = AllIcons.Nodes.UpLevel;

  public TopLevelNode(Project project, Object value) {
    super(project, value);
    myName = "[ .. ]";
    myOpenIcon = myClosedIcon = ourUpLevelIcon;
  }

  @NotNull
  public Collection<AbstractTreeNode> getChildren() {
    return Collections.emptyList();
  }

  public String getName() {
    return super.getName();
  }

  protected void update(PresentationData presentation) {
  }

}
