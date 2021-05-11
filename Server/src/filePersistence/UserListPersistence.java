package filePersistence;

import model.UserList;

public interface UserListPersistence {
    void save(UserList userList, String filename);
    UserList load(String fileName);
}
