
import java.util.*;

//Pub/Sub pattern is a wide used pattern in system design. In this problem, you need to implement a pub/sub pattern to support user subscribes on a specific channel and get notification messages from subscribed channels.
//
//        There are 3 methods you need to implement:
//
//        subscribe(channel, user_id): Subscribe the given user to the given channel.
//        unsubscribe(channel, user_id): Unsubscribe the given user from the given channel.
//        publish(channel, message): You need to publish the message to the channel so that everyone subscribed on the channel will receive this message.
//        Call PushNotification.notify(user_id, message) to push the message to the user.
//
//subscribe("group1",  1)
//        publish("group1", "hello")
//        >> user 1 received "Hello"
//        subscribe("group1", 2)
//        publish("group1", "thank you")
//        >> user 1 received "thank you"
//        >> user 2 received "thank you"
//        unsubscribe("group2", 3)
//        >> user 3 is not in group2, do nothing
//        unsubscribe("group1", 1)
//        publish("group1", "thank you very much")
//        >> user 2 received "thank you very much"
//        publish("group2", "are you ok?")
//        >> # you don't need to push this message to anyone
/* Definition of PushNotification
 * class PushNotification {
 *     public static void notify(int user_id, String the_message)
 *  };
 */
public class PubSubPattern {
    private HashMap<String, HashSet<Integer>> channels;

    public PubSubPattern(){
        channels = new HashMap<String, HashSet<Integer>>();
    }

    /**
     * @param channel:
     * @param user_id:
     * @return: nothing
     */
    public void subscribe(String channel, int user_id) {
        if (!channels.containsKey(channel)) {
            channels.put(channel, new HashSet<Integer>());
        }
        HashSet<Integer> user_ids = channels.get(channel);
        user_ids.add(user_id);
    }

    /**
     * @param channel:
     * @param user_id:
     * @return: nothing
     */
    public void unsubscribe(String channel, int user_id) {
        if (!channels.containsKey(channel)) {
            return;
        }

        HashSet<Integer> user_ids = channels.get(channel);
        user_ids.remove(user_id);
    }

    /**
     * @param channel:
     * @param message:
     * @return: nothing
     */
    public void publish(String channel, String message) {
        if (!channels.containsKey(channel)) {
            return;
        }
        for (Integer user_id : channels.get(channel)) {
            PushNotification.notify(user_id, message);
        }
    }
}
