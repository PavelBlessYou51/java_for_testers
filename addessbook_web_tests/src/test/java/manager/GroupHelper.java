package manager;

import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends HelperBase {

    public GroupHelper(ApplicationManager manager) {
        super(manager);

    }


    public void modifyGroup(GroupData group, GroupData modifiedGroup) {
        openGroupsPage();
        selectGroup(group);
        initGroupModifocation();
        fillGroupForm(modifiedGroup);
        submitGroupModification();
        returnToGroupsPage();

    }

    public void createGroup(GroupData group) {
        openGroupsPage();
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupsPage();
    }

    public void removeGroup(GroupData group) {
        openGroupsPage();
        selectGroup(group);
        removeSelectedGroups();
        returnToGroupsPage();
    }

    private void submitGroupCreation() {
        click(By.name("submit"));
    }


    private void initGroupCreation() {
        click(By.name("new"));
    }


    private void removeSelectedGroups() {
        click(By.name("delete"));
    }

    public void openGroupsPage() {
        if (!manager.isElementPresent(By.name("new"))) {
            click(By.linkText("groups"));
        }
    }


    private void returnToGroupsPage() {
        click(By.xpath("//a[contains(text(), 'groups')]"));
    }

    private void submitGroupModification() {
        click(By.name("update"));
    }

    private void fillGroupForm(GroupData group) {
        type(By.name("group_name"), group.name());
        type(By.name("group_header"), group.header());
        type(By.name("group_footer"), group.footer());
    }

    private void initGroupModifocation() {
        click(By.name("edit"));
    }

    private void selectGroup(GroupData group) {
        click(By.cssSelector(String.format("input[value='%s']", group.id())));
    }

    public int getCount() {
        openGroupsPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllGroups() {
        openGroupsPage();
        selectAllGroups();
        removeSelectedGroups();
    }

    private void selectAllGroups() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }

    public List<GroupData> getList() {
        openGroupsPage();
        ArrayList<GroupData> groups = new ArrayList<>();
        int spanCount = manager.driver.findElements(By.cssSelector("span.group")).size();
        for (int i = 0; i < spanCount; i++) {
            List<WebElement> spans = manager.driver.findElements(By.cssSelector("span.group"));
            String name = spans.get(i).getText();
            WebElement checkbox = spans.get(i).findElement(By.name("selected[]"));
            String id = checkbox.getAttribute("value");
            checkbox.click();
            initGroupModification();
            String header = manager.driver.findElement(By.cssSelector("textarea[name='group_header']")).getText();
            String footer = manager.driver.findElement(By.cssSelector("textarea[name='group_footer']")).getText();
            openGroupsPage();
            groups.add(new GroupData().withId(id).withName(name).withHeader(header).withFooter(footer));
        }
        return groups;
    }

    private void initGroupModification() {
        click(By.name("edit"));
    }
}
