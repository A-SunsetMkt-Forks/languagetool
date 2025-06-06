/* LanguageTool, a natural language style checker
 * Copyright (C) 2021 Daniel Naber (http://www.danielnaber.de)
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
package org.languagetool;

import org.junit.Test;
import org.languagetool.rules.Rule;
import org.languagetool.rules.RuleMatch;
import org.languagetool.rules.patterns.PatternRule;
import org.languagetool.rules.patterns.PatternRuleBuilderHelper;
import org.languagetool.rules.patterns.PatternToken;

import java.io.IOException;
import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class JLanguageToolTest {

  private final Language LANG = Languages.getLanguageForShortCode("xx");

  @Test
  public void testUserConfig() throws IOException {
    JLanguageTool lt1 = new JLanguageTool(LANG);
    lt1.disableRule("test_unification_with_negation");
    List<RuleMatch> matches1 = lt1.check("This is my test");
    assertThat(matches1.size(), is(0));

    List<Rule> userRules = new ArrayList<>();
    List<PatternToken> patternTokens = Arrays.asList(PatternRuleBuilderHelper.token("my"), PatternRuleBuilderHelper.token("test"));
    userRules.add(new PatternRule("MY_TEST", LANG, patternTokens, "test rule desc", "my test rule", "my test rule"));
    Map<String, Object[]> map = new HashMap<>();
    UserConfig userConfig = new UserConfig(Collections.emptyList(), userRules, map, -1, 1L, "fake", null, null, false, null, null, false, null);
    JLanguageTool lt2 = new JLanguageTool(LANG, null, userConfig);
    lt2.disableRule("test_unification_with_negation");
    List<RuleMatch> matches2 = lt2.check("This is my test");
    assertThat(matches2.size(), is(1));
  }

  @Test
  public void testCheckString() throws IOException {
    JLanguageTool jLanguageTool = new JLanguageTool(LANG);
    List<RuleMatch> ruleMatches = jLanguageTool.check("This is my test.");
    assertThat(ruleMatches.size(), is(1));
  }

  @Test
  public void testCheckStringWithCallbackReturnsTrue() throws IOException {
    JLanguageTool jLanguageTool = new JLanguageTool(LANG);
    jLanguageTool.setCheckCancelledCallback(() -> true);
    List<RuleMatch> ruleMatches = jLanguageTool.check("This is my test.");
    assertThat(ruleMatches.size(), is(0));
  }

  @Test
  public void testCheckStringWithCallbackReturnsFalse() throws IOException {
    JLanguageTool jLanguageTool = new JLanguageTool(LANG);
    jLanguageTool.setCheckCancelledCallback(() -> false);
    List<RuleMatch> ruleMatches = jLanguageTool.check("This is my test.");
    assertThat(ruleMatches.size(), is(1));
  }

}
