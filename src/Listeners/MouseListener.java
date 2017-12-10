package Listeners;

import com.leapmotion.leap.*;
import com.leapmotion.leap.Frame;

import java.awt.*;
import java.awt.event.InputEvent;

public class MouseListener extends Listener {

    public Robot robot;

    public void onConnect(Controller c) {
        c.enableGesture(Gesture.Type.TYPE_CIRCLE);
        c.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
        c.enableGesture(Gesture.Type.TYPE_SWIPE);
        c.enableGesture(Gesture.Type.TYPE_KEY_TAP);
    }

    public void onFrame(Controller c) {
        //c.setPolicy(Controller.PolicyFlag.POLICY_BACKGROUND_FRAMES);

        try {
            robot = new Robot();
        } catch (Exception e) {
        }
        Frame frame = c.frame();
        InteractionBox box = frame.interactionBox();

        for (Finger f : frame.fingers()) {
            for (Finger t : frame.fingers()) {
                for (Finger p : frame.fingers()) {
                    for (Finger m : frame.fingers()) {
                        for (Finger r : frame.fingers()) {
                            if (f.type() == Finger.Type.TYPE_INDEX) {

                                if (t.type() == Finger.Type.TYPE_THUMB) {
                                    if (p.type() == Finger.Type.TYPE_PINKY) {
                                        if (m.type() == Finger.Type.TYPE_MIDDLE) {
                                            if (r.type() == Finger.Type.TYPE_RING) {


                                                Vector indexPos = f.stabilizedTipPosition();
                                                Vector thumbPos = t.stabilizedTipPosition();
                                                Vector middlePos = p.stabilizedTipPosition();

                                                Vector notTouching1 = new Vector(20, 20, 20);
                                                Vector notTouching2 = new Vector(-20, -20, -20);

                                                float leftClickX = indexPos.get(0) - thumbPos.get(0);
                                                float leftClickY = indexPos.get(1) - thumbPos.get(1);
                                                float leftClickZ = indexPos.get(2) - thumbPos.get(2);


                                                float rightClickX = middlePos.get(0) - thumbPos.get(0);
                                                float rightClickY = middlePos.get(1) - thumbPos.get(1);
                                                float rightClickZ = middlePos.get(2) - thumbPos.get(2);

                                                Vector leftClick = new Vector(leftClickX, leftClickY, leftClickZ);
                                                Vector rightClick = new Vector(rightClickX, rightClickY, rightClickZ);


                                                /**
                                                 if((leftClick.get(0)) < (notTouching1.get(0)) && (leftClick.get(0)) > (notTouching2.get(0)))
                                                 {
                                                 if ((leftClick.get(1)) < (notTouching1.get(1)) && (leftClick.get(1)) > (notTouching2.get(1)))
                                                 {
                                                 if ((leftClick.get(2)) < (notTouching1.get(2)) && (leftClick.get(2)) > (notTouching2.get(2)))
                                                 {
                                                 robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                                                 try{Thread.sleep(50);} catch(Exception e){}
                                                 robot.mouseRelease(InputEvent.BUTTON1_MASK);
                                                 try{Thread.sleep(50);} catch(Exception e){}
                                                 }

                                                 }

                                                 }





                                                 if((rightClick.get(0)) < (notTouching1.get(0)) && (rightClick.get(0)) > (notTouching2.get(0)))
                                                 {
                                                 if ((rightClick.get(1)) < (notTouching1.get(1)) && (rightClick.get(1)) > (notTouching2.get(1)))
                                                 {
                                                 if ((rightClick.get(2)) < (notTouching1.get(2)) && (rightClick.get(2)) > (notTouching2.get(2)))
                                                 {
                                                 robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
                                                 try{Thread.sleep(50);} catch(Exception e) {}
                                                 robot.mouseRelease(InputEvent.BUTTON3_MASK);
                                                 try{Thread.sleep(50);} catch(Exception e){}


                                                 }

                                                 }

                                                 }

                                                 }
                                                 }
                                                 */
                                                if (f.isExtended() && m.isExtended() && r.isExtended() && p.isExtended()) // 4 fingers
                                                {
                                                    for (Hand h : frame.hands()) {
                                                        Vector handPos = h.palmPosition();
                                                        Vector boxHandPos = box.normalizePoint(handPos);
                                                        Dimension screen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
                                                        robot.mouseMove((int) (screen.width * boxHandPos.getX()), (int) (screen.height - boxHandPos.getY() * screen.height));
                                                    }
                                                }

                                                for (Gesture g : frame.gestures()) {
                                                    if (g.type() == Gesture.Type.TYPE_CIRCLE) {
                                                        CircleGesture circle = new CircleGesture(g);
                                                        if (f.isExtended() && !m.isExtended() && !r.isExtended() && !p.isExtended()) {
                                                            if (circle.pointable().direction().angleTo(circle.normal()) <= Math.PI / 4) {
                                                                robot.mouseWheel(1);
                                                                try {
                                                                    Thread.sleep(50);
                                                                } catch (Exception e) {
                                                                }
                                                            } else {
                                                                robot.mouseWheel(-1);
                                                                try {
                                                                    Thread.sleep(50);
                                                                } catch (Exception e) {
                                                                }
                                                            }
                                                        }
                                                    }
                                                    if (g.type() == Gesture.Type.TYPE_KEY_TAP) {
                                                        if (f.isExtended() && !m.isExtended()) { //
                                                            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                                                            try {
                                                                Thread.sleep(50);
                                                            } catch (Exception e) {
                                                            }
                                                            robot.mouseRelease(InputEvent.BUTTON1_MASK);

                                                        }
                                                        if (f.isExtended() && m.isExtended()) { // first two fingers
                                                            robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
                                                            try {
                                                                Thread.sleep(50);
                                                            } catch (Exception e) {
                                                            }
                                                            robot.mouseRelease(InputEvent.BUTTON3_MASK);
                                                        }
                                                    }
                                                    if (g.type() == Gesture.Type.TYPE_SCREEN_TAP) {
                                                        if (f.isExtended() && !m.isExtended()) { //
                                                            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                                                            try {
                                                                Thread.sleep(50);
                                                            } catch (Exception e) {
                                                            }
                                                            robot.mouseRelease(InputEvent.BUTTON1_MASK);

                                                        }
                                                        if (f.isExtended() && m.isExtended()) { // first two fingers
                                                            robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
                                                            try {
                                                                Thread.sleep(50);
                                                            } catch (Exception e) {
                                                            }
                                                            robot.mouseRelease(InputEvent.BUTTON3_MASK);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
    }
}