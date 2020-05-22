package Part01.Lesson08.Task01;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * ClientGuiModel.
 *
 * @author Roman Khokhlov
 */
public class ClientGuiModel {
    private final Set<String> allUserNames = new HashSet<String>();

    private String newMessage;

    public Set<String> getAllUserNames() {
        Set<String> newSet = Collections.unmodifiableSet(allUserNames);
        return newSet;
    }

    public String getNewMessage() {
        return newMessage;
    }

    public void setNewMessage(String newMessage) {
        this.newMessage = newMessage;
    }

    public void addUser(String newUserName) {
        allUserNames.add(newUserName);
    }

    public void deleteUser(String userName) {
        allUserNames.remove(userName);
    }

}
