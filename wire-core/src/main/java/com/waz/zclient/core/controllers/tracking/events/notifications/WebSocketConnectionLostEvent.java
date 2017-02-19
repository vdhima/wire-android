package com.waz.zclient.core.controllers.tracking.events.notifications;

import android.support.annotation.NonNull;
import com.waz.zclient.core.controllers.tracking.attributes.Attribute;
import com.waz.zclient.core.controllers.tracking.events.Event;
import org.threeten.bp.Duration;

public class WebSocketConnectionLostEvent extends Event {

    public WebSocketConnectionLostEvent(Duration duration) {
        attributes.put(Attribute.DURATION, Long.toString(duration.toMillis()));
    }

    @NonNull
    @Override
    public String getName() {
        return "notification.web_socket_closed";
    }

}
