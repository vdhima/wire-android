package com.waz.zclient.core.controllers.tracking.events.notifications;

import android.support.annotation.NonNull;
import com.waz.zclient.core.controllers.tracking.attributes.Attribute;
import com.waz.zclient.core.controllers.tracking.events.Event;
import org.threeten.bp.Duration;

/**
 * Generated when connection is lost and we only discover that when trying to ping the server.
 */
public class WebSocketConnectionLostOnPingEvent extends Event {

    public WebSocketConnectionLostOnPingEvent(Duration duration) {
        attributes.put(Attribute.DURATION, Long.toString(duration.toMillis()));
    }

    @NonNull
    @Override
    public String getName() {
        return "notification.web_socket_lost_on_ping";
    }

}
