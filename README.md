# Valut Technical Screening Questionnaire

This Java-based automation framework compares two CSV files and identifies and reports any differences between them. If the files are identical, it reports that as well. The goal is to facilitate easy triage of differences for validation and testing purposes.

---

## Prerequisites

- **Java Development Kit (JDK)**: Version 8 or higher
- **Integrated Development Environment (IDE)**: Eclipse, IntelliJ IDEA, or any Java-supporting IDE
- **Git**: For cloning the repository

---

## How to Clone the Project

### How to Clone This Project Using Command Prompt / Terminal
1.	Install Git (if you haven’t already)
   o	Download and install Git from https://git-scm.com/
   o	Make sure you can run Git commands. In Windows, open “Command Prompt” or “Git Bash” and type:
   o	git --version
2.	Get the Repository URL
   o	Go to your remote repository (on GitHub, GitLab, etc.).
   o	Click the Code or Clone button.
   o	Copy the HTTPS link (or SSH link if you have SSH keys set up).
   o	Example: https://github.com/yourusername/your-repo.git (GitHub Docs)
3.	Open Command Prompt / Terminal
   o	On Windows: Press Win + R, type cmd (or open “Git Bash”).
   o	On macOS: Use Terminal.
   o	On Linux: Use Terminal.
   4.	Navigate to the Folder Where You Want the Project
   o	Use the cd command to change directories.
   o	For example:
   o	cd C:\Users\YourName\Documents\Projects
   (Or your desired path.)
5.	Run the Clone Command
   o	Use the command:
   o	git clone <repository-url>
   o	Replace <repository-url> with the URL you copied.
   o	Example:
   o	git clone https://github.com/yourusername/your-repo.git
6.	Change Into the Cloned Directory
   o	After cloning, a new folder will be created with the repo name.
   o	Move into that folder:
   o	cd your-repo
7.	Check That the Clone Worked
   o	Use dir (Windows) or ls (macOS/Linux) to list files.
   o	You should see your project files.
   o	Also check remote connections:
   o	git remote -v
   o	It should show something like origin https://github.com/yourusername/your-repo.git (fetch) and (push)


### How  to Run the Java Application
In Eclipse:
After cloning and importing the project:

If Eclipse doesn't automatically recognize it as a Java project, right-click the project → Configure → Convert to Java Project.

Ensure your Java files are inside the src folder.

Right-click the main Java file containing the main method (e.g., CSVComparator.java) → Run As → Java Application.
