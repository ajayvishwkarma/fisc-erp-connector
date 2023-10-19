package bamboo;

import com.atlassian.bamboo.specs.builders.task.ScriptTask;
import com.atlassian.bamboo.specs.model.task.ScriptTaskProperties;
import com.atlassian.bamboo.specs.api.builders.task.Task;

import static bamboo.Constants.*;

public final class ReleaseNotes {

    //Method to update release notes
    public static Task updateReleaseNotes() {
        return new ScriptTask()
                .description("ReleaseNotes Update On Confluence")
                .interpreter(ScriptTaskProperties.Interpreter.BINSH_OR_CMDEXE)
                .inlineBody("sudo pip3 install -m pip3\npip3 install pytz\npip3 install requests\n/usr/bin/python3 ipaas-bamboo-scripts/release-notes.py "
                        + PROJECT_KEY + "-" + PLAN_KEY
                        + " bamboo_" + stashToken
                        + " bamboo_" + confluenceToken
                        + " bamboo_" + bambooToken
                        + " " + releaseNotesName
                        + " " + spaceKey
                        + " " + parentPageId
                        + " " + projectKey
                        + " " + repositoryName
                        + " " + emptyPRDescriptionFailBuild
                        + " " + latestReleaseNotesFirst );

    }
}