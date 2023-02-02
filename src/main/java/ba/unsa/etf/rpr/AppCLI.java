package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.UsersManager;
import ba.unsa.etf.rpr.mn.Users;
import org.apache.commons.cli.*;

import java.util.List;

public class AppCLI {
    public static void main(String[] args) throws ParseException {
        Options options = new Options();
        options.addOption("h", "help", false, "Print this message");
        options.addOption("u", "username", true, "Username");
        options.addOption("p", "password", true, "Password");
        options.addOption("e", "email", true, "Email");
        options.addOption("b", "balance", true, "Balance");
        options.addOption("a", "add", false, "Add new user");
        options.addOption("d", "delete", false, "Delete user");
        options.addOption("l", "list", false, "List all users");
        options.addOption("c", "change", false, "Change password");
        options.addOption("s", "search", true, "Search user");
        options.addOption("v", "version", false, "Print version");
        CommandLineParser parser = new DefaultParser();
        CommandLine cl = parser.parse(options, args);
        if (cl.hasOption("h")) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("AppCLI", options);
        }
        if (cl.hasOption("s")) {
            UsersManager usersManager = new UsersManager();
            Users user = usersManager.getUserByUsername(cl.getOptionValue("s"));
            if (user != null) {
                System.out.println(user.getUsername());
                System.out.println(user.getEmail());
                System.out.println(user.getPassword());
                System.out.println(user.getBalance());
            } else {
                System.out.println("User not found");
            }
        }
        if(cl.hasOption("l"))
        {
            UsersManager usersManager = new UsersManager();
            List<Users> users = usersManager.getAll();
            for(Users x: users)
            {
                System.out.println(x.getUsername() + " " + x.getEmail() + " " + x.getPassword() + " " + x.getBalance());
            }
        }
        if(cl.hasOption("a"))
        {
            UsersManager usersManager = new UsersManager();
            Users user = new Users();
            try
            {
                user.setUsername(cl.getOptionValue("u"));
                user.setPassword(cl.getOptionValue("p"));
                user.setEmail(cl.getOptionValue("e"));
                user.setBalance(Double.parseDouble(cl.getOptionValue("b")));
                user.setPrivilegeLevel("User");
                usersManager.add(user);
                System.out.println("User added" + user.getUsername() + " " + user.getEmail() + " " + user.getPassword() + " " + user.getBalance());
            }
            catch (Exception e)
            {
                System.out.println("Error");
            }
        }
        if(cl.hasOption("d"))
        {
            UsersManager usersManager = new UsersManager();
            Users user = usersManager.getUserByUsername(cl.getOptionValue("u"));
            if(user != null)
            {
                usersManager.delete(user.getIdUsers());
                System.out.println("User deleted");
            }
            else
            {
                System.out.println("User not found");
            }
        }
        if(cl.hasOption("c"))
        {
            UsersManager usersManager = new UsersManager();
            Users user = usersManager.getUserByUsername(cl.getOptionValue("u"));
            if(user != null)
            {
                user.setPassword(cl.getOptionValue("p"));
                usersManager.update(user);
                System.out.println("Password changed");
            }
            else
            {
                System.out.println("User not found");
            }
        }
        if(cl.hasOption("v"))
        {
            System.out.println("Version 1.0");
        }

    }
}
