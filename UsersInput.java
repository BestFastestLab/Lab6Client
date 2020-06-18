import java.time.LocalDate;
import java.util.Scanner;

public class UsersInput {
    public static Commands Input() {
        Commands objectCommand = new Commands();
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите команду или help для получения списка доступных команд");
        long k = 0;
        boolean c = true;
        while (c) {
            if (sc.hasNext()) {
                String command = sc.nextLine();//получаем команду от человека полностью
                String commandWords[] = command.split(" ");//разбиваем ее на слова
                switch (commandWords[0]) {
                    case "help":
                        objectCommand.setName("help");
                        c = false;
                        break;
                    case "info":
                        objectCommand.setName("info");
                        c = false;
                        break;
                    case "show":
                        objectCommand.setName("show");
                        c = false;
                        break;
                    case "add":
                        if (commandWords[commandWords.length - 1].equals("add")) {//случай, когда нет параметра там, где он нужен
                            System.out.println("А что добавлять?");
                        } else {
                            objectCommand.setName("add");
                            objectCommand.setBandName(commandWords[commandWords.length - 1]);
                            objectCommand.setCode((byte) 0);
                            c = false;
                        }
                        break;
                    case "update":
                        if (commandWords[commandWords.length - 1].equals("update")) {
                            System.out.println("А что обновлять?");
                        } else {
                            try {
                                k = Long.parseLong(commandWords[commandWords.length - 1]);
                            } catch (Exception e) {
                                System.out.println("Попробуйте еще раз ввести команду, используя число в качесте id");
                            }
                            objectCommand.setName("update");
                            objectCommand.setId(k);
                            objectCommand.setCode((byte) 1);
                            c = false;
                        }

                        break;
                    case "remove_by_id":
                        if (commandWords[commandWords.length - 1].equals("remove_by_id")) {
                            System.out.println("А что обновлять?");
                        } else {
                            try {
                                k = Long.parseLong(commandWords[commandWords.length - 1]);
                            } catch (Exception e) {
                                System.out.println("Попробуйте еще раз ввести команду, используя число в качесте id");
                            }
                            objectCommand.setName("remove_by_id");
                            objectCommand.setId(k);
                            c = false;
                        }

                        break;

                    case "clear":
                        objectCommand.setName("clear");
                        c = false;
                        break;
                    case "execute_script":
                        if (commandWords[commandWords.length - 1].equals("execute_script")) {
                            System.out.println("А какой скрипт выполнить?");
                        } else {
                            objectCommand.setName("execute_script");
                            objectCommand.setFileName(commandWords[commandWords.length - 1]);
                            c = false;
                        }
                        break;
                    case "exit":
                        objectCommand.setName("exit");
                        c = false;
                        break;
                    case "add_if_max":
                        if (commandWords[commandWords.length - 1].equals("add_if_max")) {
                            System.out.println("А что добавлять?");
                        } else {
                            objectCommand.setName("add_if_max");
                            objectCommand.setBandName(commandWords[commandWords.length - 1]);
                            c = false;
                        }
                        break;
                    case "remove_greater":
                        if (commandWords[commandWords.length - 1].equals("remove_greater")) {
                            System.out.println("А что удалять?");
                        } else {
                            objectCommand.setBandName(commandWords[commandWords.length - 1]);
                            objectCommand.setName("remove_greater");
                            c = false;
                        }
                        break;
                    case "history":
                        objectCommand.setName("history");
                        c = false;
                        break;
                    case "min_by_albums_count":
                        objectCommand.setName("min_by_albums_count");
                        c = false;
                        break;
                    case "count_greater_than_genre":
                        if (commandWords[commandWords.length - 1].equals("count_greater_than_genre")) {
                            System.out.println("А с чем сравнивать?");
                        } else {
                            if (MusicGenre.existence(commandWords[commandWords.length - 1])) {
                                objectCommand.setName("count_greater_than_genre");
                                objectCommand.setGenre(MusicGenre.valueOf(commandWords[commandWords.length - 1]));
                                c = false;
                            } else {
                                System.out.println("Такого жанра не существует! Попробуйте еще раз ввести команду");
                            }
                        }
                        break;
                    case "print_ascending":
                        objectCommand.setName("print_ascending");
                        c = false;
                        break;
                    default:
                        System.out.println("Введенная вами команда не соответстувет требованиям. Попробуйте еще раз");
                }
            } else {
                System.out.println("Вы нажали ctrl+d, вызвав тем самым завершение работы программы");
                System.exit(0);
            }
        }
        return objectCommand;
    }

    public static MusicBand creatingNewBand(String name) {
        MusicBand band = new MusicBand();
        Coordinates coordinates = new Coordinates();
        band.setName(name);
        System.out.println("Введите координату x(число, большее -775, но меньшее " + Double.MAX_VALUE + ")");
        double x = 0.0;
        boolean metka = true;
        while (metka) {
            try {
                Scanner scanner = new Scanner(System.in);
                x = scanner.nextDouble();
                if (x > -775) {
                    metka = false;
                } else {
                    System.out.println("Это число не больше -775! Попробуйте еще раз.");
                }
            } catch (Exception e) {
                System.out.println("Это не число, либо число не из допустимого интервала значаений, попробуйте еще раз!");
            }
        }
        System.out.println("Введите координату y(целое число в пределах от" + Long.MIN_VALUE + " до " + Long.MAX_VALUE + ")");
        metka = true;
        long y = 0;
        while (metka) {
            try {
                Scanner scanner = new Scanner(System.in);
                y = scanner.nextLong();
                metka = false;

            } catch (Exception e) {
                System.out.println("Это не число, либо число не из допустимого интервала значаений, попробуйте еще раз!");
            }
        }
        coordinates.setX(x);
        coordinates.setY(y);
        band.setCoordinates(coordinates);
        System.out.println("Введите количество участников(целое число в пределах от 0 до " + Long.MAX_VALUE + ")");
        metka = true;
        long NumberOfParts = 0;
        while (metka) {
            try {
                Scanner scanner = new Scanner(System.in);
                NumberOfParts = scanner.nextLong();
                if (NumberOfParts > 0) {
                    metka = false;
                } else {
                    System.out.println("Введенное число меньше, либо равно 0! Попробуйте еще раз");
                }
            } catch (Exception e) {
                System.out.println("Это не число, либо число не из допустимого интервала значаений, попробуйте еще раз!");
            }
        }
        band.setNumberOfParticipants(NumberOfParts);
        System.out.println("Введите число синглов(целое число в пределах от 0 до " + Long.MAX_VALUE + ")");
        long singlesCount = 0L;
        metka = true;
        while (metka) {
            try {
                Scanner scanner = new Scanner(System.in);
                singlesCount = scanner.nextLong();
                if (singlesCount > 0) {
                    metka = false;
                } else {
                    System.out.println("Введенное число меньше, либо равно 0! Попробуйте еще раз");
                }
            } catch (Exception e) {
                System.out.println("Это не число, либо число не из допустимого интервала значаений, попробуйте еще раз!");
            }
        }
        band.setSinglesCount(singlesCount);
        System.out.println("Введите число альбомов(целое число в пределах от 0 до " + Long.MAX_VALUE + ")");
        long albumsCount = 0;
        metka = true;
        while (metka) {
            try {
                Scanner scanner = new Scanner(System.in);
                albumsCount = scanner.nextLong();
                if (albumsCount > 0) {
                    metka = false;
                } else {
                    System.out.println("Введенное число меньше, либо равно 0! Попробуйте еще раз");
                }
            } catch (Exception e) {
                System.out.println("Это не число, либо число не из допустимого интервала значаений, попробуйте еще раз!");
            }
        }
        band.setAlbumsCount(albumsCount);
        String genre = null;
        while (!MusicGenre.existence(genre)) {
            System.out.println("Введите жанр. Список жанров:\n" +
                    "RAP\n" +
                    "POST_ROCK\n" +
                    "BRIT_POP\n");
            Scanner scanner = new Scanner(System.in);
            genre = scanner.nextLine();
        }
        band.setGenre(MusicGenre.valueOf(genre));
        System.out.println("Введите количество песен в лучшем альбоме(целое число от 0 до " + Integer.MAX_VALUE + ")");
        int length = 0;
        metka = true;
        while (metka) {
            try {
                Scanner scanner = new Scanner(System.in);
                length = scanner.nextInt();
                if (length > 0) {
                    metka = false;
                } else {
                    System.out.println("Введенное число меньше, либо равно 0! Попробуйте еще раз");
                }
            } catch (Exception e) {
                System.out.println("Это не число, либо число не из допустимого интервала значаений, попробуйте еще раз!");
            }
        }
        System.out.println("Введите его название");
        Scanner scanner = new Scanner(System.in);
        String nameOfBestAlbum = scanner.nextLine();
        Album bestAlbum = new Album(nameOfBestAlbum, length);
        band.setBestAlbum(bestAlbum);
        band.setCreationDate(LocalDate.of((int) (Math.random() * 2021), (int) (Math.random() * 12 + 1), (int) (Math.random() * 28 + 1)));
        return band;
    }

}


