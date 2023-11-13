package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeBeanTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        System.out.println("find PrototypeBean1");
        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find PrototypeBean2");
        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);

        System.out.println("prototypeBean1 = " + bean1);
        System.out.println("prototypeBean2 = " + bean2);

        assertThat(bean1).isNotSameAs(bean2);

        ac.close();

        //bean1.destroy();
        //bean2.destroy();
    }

    @Scope("prototype")
    static class PrototypeBean {

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destory");
        }

    }

}
