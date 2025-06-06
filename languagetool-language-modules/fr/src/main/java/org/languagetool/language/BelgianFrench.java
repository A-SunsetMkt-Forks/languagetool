/* LanguageTool, a natural language style checker
 * Copyright (C) 2023 Daniel Naber (http://www.danielnaber.de)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301
 * USA
 */
package org.languagetool.language;

import java.util.Collections;
import java.util.List;

public class BelgianFrench extends French {

  private static volatile Throwable instantiationTrace;

  public BelgianFrench() {
    super(true);
    Throwable trace = instantiationTrace;
    if (trace != null) {
      throw new RuntimeException("Language was already instantiated, see the cause stacktrace below.", trace);
    }
    instantiationTrace = new Throwable();
  }

  @Override
  public String getName() {
    return "French (Belgium)";
  }

  @Override
  public String[] getCountries() {
    return new String[] { "BE" };
  }

  @Override
  public List<String> getDefaultDisabledRulesForVariant() {
    List<String> rules = Collections.singletonList("DOUBLER_UNE_CLASSE");
    return rules;
  }
}
