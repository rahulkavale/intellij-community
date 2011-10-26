/*
 * Copyright 2000-2011 JetBrains s.r.o.
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
package com.intellij.conversion.impl;

import com.intellij.conversion.CannotConvertException;
import com.intellij.conversion.XmlBasedSettings;
import org.jdom.Document;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class XmlBasedSettingsImpl implements XmlBasedSettings {
  protected final SettingsXmlFile mySettingsFile;
  protected final ConversionContextImpl myContext;

  public XmlBasedSettingsImpl(File file, ConversionContextImpl context) throws CannotConvertException {
    myContext = context;
    mySettingsFile = context.getOrCreateFile(file);
  }

  @NotNull
  public Element getRootElement() {
    return mySettingsFile.getRootElement();
  }

  public File getFile() {
    return mySettingsFile.getFile();
  }
}
