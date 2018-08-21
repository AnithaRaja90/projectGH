import { browser, protractor } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { PickListValueComponentsPage, PickListValueUpdatePage } from './pick-list-value.page-object';

describe('PickListValue e2e test', () => {
    let navBarPage: NavBarPage;
    let pickListValueUpdatePage: PickListValueUpdatePage;
    let pickListValueComponentsPage: PickListValueComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load PickListValues', () => {
        navBarPage.goToEntity('pick-list-value');
        pickListValueComponentsPage = new PickListValueComponentsPage();
        expect(pickListValueComponentsPage.getTitle()).toMatch(/projectGhApp.pickListValue.home.title/);
    });

    it('should load create PickListValue page', () => {
        pickListValueComponentsPage.clickOnCreateButton();
        pickListValueUpdatePage = new PickListValueUpdatePage();
        expect(pickListValueUpdatePage.getPageTitle()).toMatch(/projectGhApp.pickListValue.home.createOrEditLabel/);
        pickListValueUpdatePage.cancel();
    });

    it('should create and save PickListValues', () => {
        pickListValueComponentsPage.clickOnCreateButton();
        pickListValueUpdatePage.setPickListValueInput('pickListValue');
        expect(pickListValueUpdatePage.getPickListValueInput()).toMatch('pickListValue');
        pickListValueUpdatePage.setStatusInput('5');
        expect(pickListValueUpdatePage.getStatusInput()).toMatch('5');
        pickListValueUpdatePage.setCreatedByInput('5');
        expect(pickListValueUpdatePage.getCreatedByInput()).toMatch('5');
        pickListValueUpdatePage.setModifiedByInput('5');
        expect(pickListValueUpdatePage.getModifiedByInput()).toMatch('5');
        pickListValueUpdatePage.setCreatedAtInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
        expect(pickListValueUpdatePage.getCreatedAtInput()).toContain('2001-01-01T02:30');
        pickListValueUpdatePage.setUpdatedAtInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
        expect(pickListValueUpdatePage.getUpdatedAtInput()).toContain('2001-01-01T02:30');
        pickListValueUpdatePage.pickListSelectLastOption();
        pickListValueUpdatePage.pickValueSelectLastOption();
        pickListValueUpdatePage.save();
        expect(pickListValueUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
