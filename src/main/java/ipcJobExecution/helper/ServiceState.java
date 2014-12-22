package ipcJobExecution.helper;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by MMO on 22.12.2014.
 */

public class ServiceState implements Serializable {

    public static final String DEFAULT_DATE_TIME_PATTERN = "dd.MM.yyyy HH:mm:ssZ";
    private static final long serialVersionUID = 8022644631763612666L;
    private long lastProcessTime = 0;
    private long lastResetTime = 0;

    private boolean processing = false;
    private int state = STATE_UNINITIALIZED;
    private int command = CMD_NONE;

    private int health = HEALTH_OK;

    private List<String> lastMessages = new ArrayList<String>();

    private long nextExecution = -1;
    public static final int STATE_UNINITIALIZED = -1;
    public static final int STATE_STOPPED = 0;

    public static final int STATE_RUNNING = 1;
    public static final int CMD_NONE = 0;
    public static final int CMD_STOP = 2;
    public static final int CMD_RESET = 3;

    public static final int CMD_SHUTDOWN = 4;
    public static final int HEALTH_OK = 0;
    public static final int HEALTH_WARN = 1;

    public static final int HEALTH_ERROR = 2;
    private static final SimpleDateFormat messageTimestampFormat = new SimpleDateFormat(DEFAULT_DATE_TIME_PATTERN);

    public boolean isProcessing() {
        return processing;
    }

    public void setProcessing(boolean processing) {
        this.processing = processing;
    }

    public boolean isShutdown() {
        return state == STATE_UNINITIALIZED;
    }

    public void setShutdown(boolean shutdown) {
        this.state = (shutdown ? STATE_UNINITIALIZED : STATE_STOPPED);
    }

    public boolean isRunning() {
        return state == STATE_RUNNING;
    }

    public void setRunning(boolean running) {
        this.state = (running ? STATE_RUNNING : STATE_STOPPED);
    }

    public boolean isResetRequested() {
        return command == CMD_RESET;
    }

    public void setResetRequested() {
        this.command = CMD_RESET;
    }

    public void setStopRequested() {
        this.command = CMD_STOP;
    }

    public boolean isStopRequested() {
        return command == CMD_STOP;
    }

    public void setShutdownRequested() {
        command = CMD_SHUTDOWN;
    }

    public boolean isShutdownRequested() {
        return command == CMD_SHUTDOWN;
    }

    public void resetCommand() {
        this.command = CMD_NONE;
    }

    public long getLastProcessTime() {
        return lastProcessTime;
    }

    public void setLastProcessTime(long lastProcessTime) {
        this.lastProcessTime = lastProcessTime;
    }

    public long getNextExecution() {
        if (isRunning()) {
            return nextExecution;
        } else {
            return -1;
        }
    }

    public void setNextExecution(long nextExecution) {
        this.nextExecution = nextExecution;
    }

    public int getState() {
        return state;
    }

    public int getCommand() {
        return command;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public List<String> getLastMessages() {
        return new ArrayList<String>(lastMessages);
    }

    public void clearMessages() {
        lastMessages.clear();
    }

    public void addMessage(String message) {
        try {
            lastMessages.add(0, messageTimestampFormat.format(new Date()) + " " + message);
            if (lastMessages.size() > 50) {
                lastMessages.remove(lastMessages.size() - 1);
            }
        } catch (Throwable ignore) {
        }
    }

    public long getLastResetTime() {
        return lastResetTime;
    }

    public void setLastResetTime(long lastResetTime) {
        this.lastResetTime = lastResetTime;
    }

}
