package ldh.fx.transition;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.util.Duration;

/**
 * Animate a fade in up effect on a node
 * 
 * Port of FadeInUp from Animate.css http://daneden.me/animate by Dan Eden
 * 
 * {@literal @}keyframes fadeInUp {
 * 	0% {
 * 		opacity: 0;
 * 		transform: translateY(20px);
 * 	}
 * 	100% {
 * 		opacity: 1;
 * 		transform: translateY(0);
 * 	}
 * }
 * 
 * @author Jasper Potts
 */
public class FadeInUpTransition extends CachedTimelineTransition {
    /**
     * Create new FadeInUpTransition
     * 
     * @param node The node to affect
     */
    public FadeInUpTransition(final Region node) {
        super(
            node,
              new Timeline(
                    new KeyFrame(Duration.millis(0),    
                        new KeyValue(node.opacityProperty(), 0, WEB_EASE),
                        new KeyValue(node.translateYProperty(), 20, WEB_EASE)
                    ),
                    new KeyFrame(Duration.millis(500),
                        new KeyValue(node.opacityProperty(), 1, WEB_EASE),
                        new KeyValue(node.translateYProperty(), 0, WEB_EASE)
                    )
                )
            );
        setCycleDuration(Duration.seconds(0.5));
        setDelay(Duration.seconds(0.2));
    }
}
