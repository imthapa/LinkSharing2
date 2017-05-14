package linksharing

class ReadingItemController {

    def index() { }

    def changeIsRead(Long id, Boolean isRead){
        log.info("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj$id     pppp n      $isRead")
        ReadingItem.changeIsRead(id,isRead)

    }
}
