/*
 * Created on 27-Aug-2004
 * 
 * (c) 2003-2004 ThoughtWorks Ltd
 *
 * See license.txt for license details
 */
package example.givens;

import com.thoughtworks.jbehave.extensions.story.domain.Environment;
import com.thoughtworks.jbehave.extensions.story.domain.GivenUsingMiniMock;


/** balance = -50, overdraft limit = 100 */
public class AccountIsOverdrawnWithPermission extends GivenUsingMiniMock {
    public void setUp(Environment context) throws Exception {
        new AccountIsOverdrawn().setUp(context);
        new AccountHasOverdraftPermission().setUp(context);
    }

    public String getDescription() {
        return "balance = -50, overdraft limit = 100";
    }
}
