import { browser, protractor } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { ShadeAreaComponentsPage, ShadeAreaUpdatePage } from './shade-area.page-object';

describe('ShadeArea e2e test', () => {
    let navBarPage: NavBarPage;
    let shadeAreaUpdatePage: ShadeAreaUpdatePage;
    let shadeAreaComponentsPage: ShadeAreaComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load ShadeAreas', () => {
        navBarPage.goToEntity('shade-area');
        shadeAreaComponentsPage = new ShadeAreaComponentsPage();
        expect(shadeAreaComponentsPage.getTitle()).toMatch(/projectGhApp.shadeArea.home.title/);
    });

    it('should load create ShadeArea page', () => {
        shadeAreaComponentsPage.clickOnCreateButton();
        shadeAreaUpdatePage = new ShadeAreaUpdatePage();
        expect(shadeAreaUpdatePage.getPageTitle()).toMatch(/projectGhApp.shadeArea.home.createOrEditLabel/);
        shadeAreaUpdatePage.cancel();
    });

    it('should create and save ShadeAreas', () => {
        shadeAreaComponentsPage.clickOnCreateButton();
        shadeAreaUpdatePage.setNoOfSeedlingsInput('5');
        expect(shadeAreaUpdatePage.getNoOfSeedlingsInput()).toMatch('5');
        shadeAreaUpdatePage.setDateInput('2000-12-31');
        expect(shadeAreaUpdatePage.getDateInput()).toMatch('2000-12-31');
        shadeAreaUpdatePage.setStatusInput('5');
        expect(shadeAreaUpdatePage.getStatusInput()).toMatch('5');
        shadeAreaUpdatePage.setCreatedByInput('5');
        expect(shadeAreaUpdatePage.getCreatedByInput()).toMatch('5');
        shadeAreaUpdatePage.setModifiedByInput('5');
        expect(shadeAreaUpdatePage.getModifiedByInput()).toMatch('5');
        shadeAreaUpdatePage.setCreatedAtInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
        expect(shadeAreaUpdatePage.getCreatedAtInput()).toContain('2001-01-01T02:30');
        shadeAreaUpdatePage.setUpdatedAtInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
        expect(shadeAreaUpdatePage.getUpdatedAtInput()).toContain('2001-01-01T02:30');
        shadeAreaUpdatePage.setDamageInput('5');
        expect(shadeAreaUpdatePage.getDamageInput()).toMatch('5');
        shadeAreaUpdatePage.setSaplingsInput('5');
        expect(shadeAreaUpdatePage.getSaplingsInput()).toMatch('5');
        shadeAreaUpdatePage.batchSelectLastOption();
        shadeAreaUpdatePage.save();
        expect(shadeAreaUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
