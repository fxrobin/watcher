package fr.fxjavadevblog.watcher;

import fr.jcgay.notification.Icon;
import fr.jcgay.notification.Notification;
import fr.jcgay.notification.Notifier;
import fr.jcgay.notification.SendNotification;

/**
 * Facade to send notification messages on the user desktop.
 * 
 * @author robin
 *
 */
public class NotifierFacade {
	
	private static Icon icon = Icon.create(NotifierFacade.class.getResource("/watcher-notification.png"), "watcher-notification-icon");
	
	/**
	 * send a notification to the desktop.
	 * 
	 * @param title
	 * @param message
	 */
	public static void send(String title, String message)
	{
		Notifier notifier = new SendNotification().initNotifier();
		try
		{
			Notification notification = Notification.builder().title(title).message(message).icon(icon).build();
			notifier.send(notification);
		}
		finally {
			notifier.close();
		}
	}

}
