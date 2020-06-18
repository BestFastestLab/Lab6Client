import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    final static int bufferSize = 32768;
    static byte[] b = new byte[bufferSize];
    static byte[] c = new byte[bufferSize];
    static InetAddress address;
    final static int timeout = 5000; //максимальное время ожидания ответа от сервера
    final static int port = 2345;
    static String result = " ";
    static boolean script = false;


    public static void main(String[] args) throws Exception {
        address = InetAddress.getLocalHost();
        DatagramSocket datagramSocket = new DatagramSocket();//пытаемся отправить запрос на сервер
        DatagramPacket sent = new DatagramPacket(c, bufferSize, address, port);
        while (true) {
            Commands command;
            if (!script) {
                command = UsersInput.Input();
                if (command.getName().equals("exit")) {
                    System.exit(0);
                }
                send(command, datagramSocket, sent);
            }
            command = receive(datagramSocket);
            command = resultTreatment(command, datagramSocket, sent);
            System.out.println(command.getResult());
            System.out.println(" ");
        }
    }


    public static byte[] serialize(Commands command) throws IOException {
        try (ByteArrayOutputStream b = new ByteArrayOutputStream()) {
            try (ObjectOutputStream o = new ObjectOutputStream(b)) {
                o.writeObject(command);
            }
            return b.toByteArray();
        }
    }


    public static Commands creationTime(Commands command, DatagramSocket datagramSocket, DatagramPacket datagramPacket) throws Exception {
        if (command.getResult().equals("It's creation time!")) {
            command.setBand(UsersInput.creatingNewBand(command.getBandName()));
            send(command, datagramSocket, datagramPacket);
            command = receive(datagramSocket);
        }
        return command;
    }

    public static Commands resultTreatment(Commands command, DatagramSocket datagramSocket, DatagramPacket datagramPacket) throws Exception {
        command = creationTime(command, datagramSocket, datagramPacket);
        if (command.getResult().equals("exit")) {
            System.exit(0);
        }
        if (command.getMasOfCommands().size() != 0) {
            for (Commands s : command.getMasOfCommands()) {
                s.setResult(null);
                System.out.println(s);
                send(s, datagramSocket, datagramPacket);
                command = resultTreatment(receive(datagramSocket), datagramSocket, datagramPacket);
                System.out.println(command.getResult());
                System.out.println(" ");
            }
            command.setResult("Скрипт выполнен!");
        }
        if (command.getResult().equals("Waiting for name")) {
            System.out.println("Введите имя объекта");
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNext()) {
                while (command.getBandName() == null) {
                    command.setBandName(scanner.nextLine());
                }
                command.setName("add");
                send(command, datagramSocket, datagramPacket);
                command = receive(datagramSocket);
                command = creationTime(command, datagramSocket, datagramPacket);
            } else {
                System.out.println("АААА, ctrl+d");
                System.exit(0);
            }
        }
        return command;
    }

    public static void send(Commands command, DatagramSocket datagramSocket, DatagramPacket datagramPacket) throws Exception {
        b = serialize(command);
        System.arraycopy(b, 0, c, 0, b.length);
        datagramSocket.send(datagramPacket);
    }

    public static Commands receive(DatagramSocket datagramSocket) throws Exception {
        datagramSocket.setSoTimeout(timeout);
        //отправили, стараемся получить назад
        Commands commands = new Commands();
        try {
            DatagramPacket received = new DatagramPacket(c, c.length);
            datagramSocket.receive(received);
            commands = (Commands) deserialize(c);
            /*if (commands.getResult().substring(commands.getResult().length() - 1).equals("+")) {
                commands.setResult(commands.getResult().substring(0, commands.getResult().length() - 1));
                script = true;
            } else script = false;*/
            Arrays.fill(c, (byte) 0);
        } catch (SocketTimeoutException e) {
            System.out.println("Увы, ответа нет. Видимо, имеет смысл включить сервер...");
            System.exit(0);
        }
        return commands;
    }


    public static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream b = new ByteArrayInputStream(bytes)) {
            try (ObjectInputStream o = new ObjectInputStream(b)) {
                return o.readObject();
            }
        }
    }
}
