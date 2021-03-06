
import com.jogamp.opengl.util.Animator;

import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SimpleScene extends ATestCase {

    private double theta = 0;
    private double s = 0;
    private double c = 0;
    private FPSCounter fpsCounter;


    public void display(GLAutoDrawable drawable) {
        update();
        render(drawable);
    }


    public void dispose(GLAutoDrawable drawable) {}


    public void init(GLAutoDrawable drawable) {
        GL2 gl2 = drawable.getGL().getGL2();

        //Remove innate frameRate cap VSync
        gl2.setSwapInterval(0);

        //make fps counter update every 20 frames
        drawable.getAnimator().setUpdateFPSFrames(20, null);
        fpsCounter = new FPSCounter(drawable, 12);

        /*
        //TODO LOAD RIGHT FILES HERE
        final ShaderCode vp0 = ShaderCode.create(gl2, GL2ES2.GL_VERTEX_SHADER, this.getClass(), "shader", "shader/bin", "RedSquareShader", true);
        final ShaderCode fp0 = ShaderCode.create(gl2, GL2ES2.GL_FRAGMENT_SHADER, this.getClass(), "shader", "shader/bin", "RedSquareShader", true);

        vp0.defaultShaderCustomization(gl2, true, true);
        fp0.defaultShaderCustomization(gl2, true, true);

        final ShaderProgram sp0 = new ShaderProgram();
        sp0.add(gl2, vp0, System.err);
        sp0.add(gl2, fp0, System.err);
        */
    }


    public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {}

    private void update() {
        theta += 0.01;
        s = Math.sin(theta);
        c = Math.cos(theta);
    }

    private void render(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();


        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        // draw a triangle filling the window
        gl.glBegin(GL.GL_TRIANGLES);
        gl.glColor3f(1, 0, 0);
        gl.glVertex2d(-c, -c);
        gl.glColor3f(0, 1, 0);
        gl.glVertex2d(0, c);
        gl.glColor3f(0, 0, 1);
        gl.glVertex2d(s, -s);
        gl.glEnd();

        fpsCounter.draw();
        result = "" + fpsCounter.getAvgFps();
    }
}