package co.coderiver.facebooklogin_sample;

/**
 * Created by polavarapu on 12/5/2016.
 */



        import java.lang.annotation.ElementType;
        import java.lang.annotation.Retention;
        import java.lang.annotation.RetentionPolicy;
        import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface AnimateMethod {
}
