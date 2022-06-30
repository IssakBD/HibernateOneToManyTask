package org.example;

import org.example.model.Director;
import org.example.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.awt.dnd.DragGestureEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */

/*
                    Задача
Вам дан SQL код для создания двух таблиц (Режиссер, Фильм). Также,
вам дан SQL код для заполнения этих таблиц данными.
Ссылка на SQL: https://gist.github.com/NeilAlishev/
628b0c3f368739d70f5c1ea3626fcd1e
Вам необходимо:
1) Создать Java приложение с подключенным Hibernate, которое имеет две
сущности - Режиссер и Фильм. Эти две сущности связаны с таблицами в
БД. В качестве стартового приложения можно использовать HibernateApp
(https://github.com/NeilAlishev/SpringCourse/tree/master/HibernateApp).
2) С помощью Hibernate получите любого режиссера, а затем получите список
его фильмов.
3) Получите любой фильм, а затем получите его режиссера.
4) Добавьте еще один фильм для любого режиссера.
5) Создайте нового режиссера и новый фильм и свяжите эти сущности.
6) Смените режиссера у существующего фильма.
7) Удалите фильм у любого режиссера.

*/

public class App {
    public static void main(String[] args) {

        //Подключаем конфигурацию из файла hibernate.properties (hibernate сам получает из него конфигурацию под капотом).
        Configuration configuration = new Configuration().addAnnotatedClass(Director.class).addAnnotatedClass(Movie.class); //Этой конфигурации мы должны передать класс, который является нашей сущностью (класс который помечен @Entity)
        //Теперь Hibernate будет видеть этот класс Person и будет понимать что в базе есть таблица Person которая соответствует классу Person.

        //Получаем SessionFactory чтобы из него получить сессию для работы с Hibernate
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            //2

//    Director director = session.get(Director.class, 1);
//    System.out.println(director.getMovies());


            //3

//            Movie movie = session.get(Movie.class, 5);
//            System.out.println(movie.getDirector());

            //4

//            Director director = session.get(Director.class, 5);
//            Movie movie = new Movie(director, "Unstoppable", 1996);
//            director.getMovies().add(movie);
//            session.save(movie);

            //5
//            Director director = new Director("Ruslan Gammasov", 25, new ArrayList<Movie>());
//            Movie movie = new Movie(director, "570 tenge", 2022);
//            director.getMovies().add(movie);
//            session.save(director);
//            session.save(movie);

            //6
//            Movie movie = session.get(Movie.class, 12);
//            Director director = session.get(Director.class, 7);
//
//            movie.getDirector().getMovies().remove(movie);
//
//            movie.setDirector(director);
//
//            director.getMovies().add(movie);

            //7
            Director director = session.get(Director.class, 7);

            List<Movie> movieList = director.getMovies();

            for (Movie movie : movieList) {
                session.remove(movie);
            }

            director.getMovies().clear();

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
