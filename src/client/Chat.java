/** Author: Daniel Coleman 994887
 *  Date: 23/05/2021
 */

package client;

import java.io.Serializable;
import java.time.LocalTime;

/** Chatbox message abstraction **/
public class Chat implements Serializable {

    private User user;
    private String message;
    private LocalTime timeSent;

    public Chat(User user, String message, LocalTime timeSent) {
        this.user = user;
        this.message = message;
        this.timeSent = timeSent;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalTime getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(LocalTime timeSent) {
        this.timeSent = timeSent;
    }
}
