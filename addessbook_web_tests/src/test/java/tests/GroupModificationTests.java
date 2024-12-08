package tests;

import common.CommonFunctions;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class GroupModificationTests extends TestBase {


    @Test
    void canModifyGroup() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        List<GroupData> oldGroups = app.hbm().getGroupList();
        Random rnd = new Random();
        int index = rnd.nextInt(oldGroups.size());
        GroupData testData = new GroupData().withName(CommonFunctions.randomString(10));
        app.groups().modifyGroup(oldGroups.get(index), testData);
        List<GroupData> newGroups = app.hbm().getGroupList();
        ArrayList<GroupData> expectedList = new ArrayList<>(oldGroups);
        expectedList.set(index, testData.withId(oldGroups.get(index).id()));
        Assertions.assertEquals(Set.copyOf(newGroups), Set.copyOf(expectedList));

    }
}

