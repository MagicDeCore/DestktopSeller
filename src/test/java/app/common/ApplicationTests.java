package app.common;

import app.Application;
import javafx.embed.swing.JFXPanel;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ApplicationTests {

    @BeforeClass
    public static void bootstrapJavaFx(){
      new JFXPanel();
    }

    @Test
    public void contextLoads() {
    }

}
