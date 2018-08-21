import { browser, protractor } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { NurseryComponentsPage, NurseryUpdatePage } from './nursery.page-object';

describe('Nursery e2e test', () => {
    let navBarPage: NavBarPage;
    let nurseryUpdatePage: NurseryUpdatePage;
    let nurseryComponentsPage: NurseryComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Nurseries', () => {
        navBarPage.goToEntity('nursery');
        nurseryComponentsPage = new NurseryComponentsPage();
        expect(nurseryComponentsPage.getTitle()).toMatch(/projectGhApp.nursery.home.title/);
    });

    it('should load create Nursery page', () => {
        nurseryComponentsPage.clickOnCreateButton();
        nurseryUpdatePage = new NurseryUpdatePage();
        expect(nurseryUpdatePage.getPageTitle()).toMatch(/projectGhApp.nursery.home.createOrEditLabel/);
        nurseryUpdatePage.cancel();
    });

    it('should create and save Nurseries', () => {
        nurseryComponentsPage.clickOnCreateButton();
        nurseryUpdatePage.setNurseryNameInput('nurseryName');
        expect(nurseryUpdatePage.getNurseryNameInput()).toMatch('nurseryName');
        nurseryUpdatePage.setNurseryAddressInput('nurseryAddress');
        expect(nurseryUpdatePage.getNurseryAddressInput()).toMatch('nurseryAddress');
        nurseryUpdatePage.setNurseryInchargeInput('nurseryIncharge');
        expect(nurseryUpdatePage.getNurseryInchargeInput()).toMatch('nurseryIncharge');
        nurseryUpdatePage.setStatusInput('5');
        expect(nurseryUpdatePage.getStatusInput()).toMatch('5');
        nurseryUpdatePage.setCreatedByInput('5');
        expect(nurseryUpdatePage.getCreatedByInput()).toMatch('5');
        nurseryUpdatePage.setModifiedByInput('5');
        expect(nurseryUpdatePage.getModifiedByInput()).toMatch('5');
        nurseryUpdatePage.setCreatedAtInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
        expect(nurseryUpdatePage.getCreatedAtInput()).toContain('2001-01-01T02:30');
        nurseryUpdatePage.setUpdatedAtInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
        expect(nurseryUpdatePage.getUpdatedAtInput()).toContain('2001-01-01T02:30');
        nurseryUpdatePage.sectorSelectLastOption();
        nurseryUpdatePage.nurseryTypeSelectLastOption();
        nurseryUpdatePage.save();
        expect(nurseryUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
