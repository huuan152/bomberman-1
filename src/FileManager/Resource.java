package FileManager;

public interface Resource<E> {

    E load(final String s, final int type);

}