package exercise;

import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class App {

    private static final List<Map<String, String>> USERS = Data.getUsers();

    private static final Integer DEFAULT_PER = 5;

    private static final Integer DEFAULT_PAGE = 1;


    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // BEGIN
        app.get("/users", ctx -> {
            Integer page = ctx.queryParamAsClass("page", Integer.class).getOrDefault(DEFAULT_PAGE);
            Integer per = ctx.queryParamAsClass("per", Integer.class).getOrDefault(DEFAULT_PER);
            ctx.json(USERS.subList((page - 1) * per, page * per));
        });
        // END

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
