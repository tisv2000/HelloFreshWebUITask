package core.base_page;

import core.driver.DriverHolder;
import core.exceptions.PageInstantiationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PageFactory {

    public static <T extends URLProvider> T createPage(Class<T> pageClass) {
        try {
            log.info("Create instance of {}", pageClass.getSimpleName());
            return pageClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("Can't create instance of {} class ({})", pageClass.getName(), e.getMessage());
            throw new PageInstantiationException("Can't create instance of " + pageClass.getName() + " class (" + e.getMessage() + ")");
        }
    }

    public static <T extends URLProvider> T openNewPage(Class<T> pageClass) {
        T page = createPage(pageClass);
        log.info("Open page {} in browser", pageClass.getSimpleName());
        log.info("Page URL: {}", page.getURL());
        DriverHolder.getDriver().get(page.getURL());
        return page;
    }
}
