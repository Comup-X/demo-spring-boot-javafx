package com.comup.demo.springboot.javafx

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.stage.Stage
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ConfigurableApplicationContext

@SpringBootApplication
class DemoSpringBootJavafxApplication : Application() {

    private companion object {
        lateinit var args: Array<String>

        @JvmStatic fun main(args: Array<String>) {
            this.args = args
            Application.launch(DemoSpringBootJavafxApplication::class.java, *args)
        }
    }

    private lateinit var applicationContext: ConfigurableApplicationContext

    override fun start(primaryStage: Stage?) {
        val resourceAsStream = this.javaClass.getResourceAsStream("/loading.gif")
        val image = ImageView(Image(resourceAsStream))
        val root = HBox(image)

        val testButton = Button("test")

        testButton.setOnAction {
            println("asdfasd")
            testButton.text = applicationContext.getBean(T::class.java).x
        }
        root.children.add(testButton)
        primaryStage!!.scene = Scene(root)
        primaryStage.show()
        Thread(Runnable {
            applicationContext = SpringApplication.run(this.javaClass, *args)
            applicationContext.autowireCapableBeanFactory.autowireBean(this.javaClass)
        }).start()
    }

    override fun stop() {
        super.stop()
        applicationContext.close()
    }
}


//package com.ch.dcs.security.check
//
//import com.ch.dcs.security.check.base.BaseController
//import com.ch.dcs.security.check.common.util.FXMLUtil
//import com.ch.dcs.security.check.counter.constant.SceneEnum
//import javafx.application.Application
//import javafx.application.Preloader
//import javafx.scene.Scene
//import javafx.scene.image.Image
//import javafx.scene.input.KeyCombination
//import javafx.stage.Stage
//import javafx.stage.StageStyle
//import org.slf4j.Logger
//import org.slf4j.LoggerFactory
//import org.springframework.boot.SpringApplication
//import org.springframework.boot.autoconfigure.SpringBootApplication
//import org.springframework.context.ConfigurableApplicationContext
//import org.springframework.context.annotation.Lazy
//
//@Lazy
//@SpringBootApplication
//class DcsSecurityCheckApplication : Application() {
//
//    @Throws(Exception::class)
//    override fun start(primaryStage: Stage) {
//        DcsSecurityCheckApplication.primaryStage = primaryStage   //设置全局引用
//        notifyPreloader(Preloader.StateChangeNotification(Preloader.StateChangeNotification.Type.BEFORE_START))
//        primaryStage.setOnCloseRequest { event -> System.exit(0) }
//        val scene = Scene(FXMLUtil.load(this, SceneEnum.LOGIN_SCENE.getFxmlPath()))
//        primaryStage.scene = scene
//        primaryStage.title = SceneEnum.LOGIN_SCENE.getTitle()
//        primaryStage.centerOnScreen()
//        primaryStage.initStyle(StageStyle.UNDECORATED)
//        //全屏代码
//        //        primaryStage.setFullScreen(true);
//        primaryStage.fullScreenExitKeyCombination = KeyCombination.NO_MATCH
//        BaseController.setStage(primaryStage)
//        //设置标题图标
//        primaryStage.icons.add(Image("./images/logo.png"))
//        primaryStage.show()
//    }
//
//    @Throws(Exception::class)
//    override fun init() {
//        applicationContext = SpringApplication.run(javaClass, *args)
//        applicationContext.autowireCapableBeanFactory.autowireBean(this)
//    }
//
//    @Throws(Exception::class)
//    override fun stop() {
//        super.stop()
//        applicationContext.close()
//    }
//
//    companion object {
//
//        private val LOGGER = LoggerFactory.getLogger(DcsSecurityCheckApplication::class.java)
//
//        private var args: Array<String>? = null
//
//        var applicationContext: ConfigurableApplicationContext
//
//        var primaryStage: Stage
//
//        @JvmStatic fun main(args: Array<String>) {
//            DcsSecurityCheckApplication.args = args
//            Application.launch(DcsSecurityCheckApplication::class.java, *args)
//        }
//    }
//}