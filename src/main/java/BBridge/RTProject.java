package BBridge;

import java.util.ArrayList;

public record RTProject(String request, boolean successful,String message, ArrayList<Project> data)
{}
