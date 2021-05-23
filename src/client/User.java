/** Author: Daniel Coleman 994887
 *  Date: 23/05/2021
 */

package client;

import java.io.Serializable;

/** Whiteboard user abstraction */
public class User implements Serializable {

    private String username;
    private int status;

    public User(String username, int status) {
        this.username = username;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
