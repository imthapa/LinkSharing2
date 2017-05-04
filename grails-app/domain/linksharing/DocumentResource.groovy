package linksharing

class DocumentResource extends Resource{

    String filePath;

    static constraints = {
        filePath(nullable: false)
    }

    @Override
    String toString() {
        return "$filePath"
    }
}
