import { browser, protractor } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { PickListComponentsPage, PickListUpdatePage } from './pick-list.page-object';

describe('PickList e2e test', () => {
    let navBarPage: NavBarPage;
    let pickListUpdatePage: PickListUpdatePage;
    let pickListComponentsPage: PickListComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load PickLists', () => {
        navBarPage.goToEntity('pick-list');
        pickListComponentsPage = new PickListComponentsPage();
        expect(pickListComponentsPage.getTitle()).toMatch(/projectGhApp.pickList.home.title/);
    });

    it('should load create PickList page', () => {
        pickListComponentsPage.clickOnCreateButton();
        pickListUpdatePage = new PickListUpdatePage();
        expect(pickListUpdatePage.getPageTitle()).toMatch(/projectGhApp.pickList.home.createOrEditLabel/);
        pickListUpdatePage.cancel();
    });

    it('should create and save PickLists', () => {
        pickListComponentsPage.clickOnCreateButton();
        pickListUpdatePage.setPickListNameInput('pickListName');
        expect(pickListUpdatePage.getPickListNameInput()).toMatch('pickListName');
        pickListUpdatePage.setStatusInput('5');
        expect(pickListUpdatePage.getStatusInput()).toMatch('5');
        pickListUpdatePage.setCreatedByInput('5');
        expect(pickListUpdatePage.getCreatedByInput()).toMatch('5');
        pickListUpdatePage.setModifiedByInput('5');
        expect(pickListUpdatePage.getModifiedByInput()).toMatch('5');
        pickListUpdatePage.setCreatedAtInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
        expect(pickListUpdatePage.getCreatedAtInput()).toContain('2001-01-01T02:30');
        pickListUpdatePage.setUpdatedAtInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
        expect(pickListUpdatePage.getUpdatedAtInput()).toContain('2001-01-01T02:30');
        pickListUpdatePage.save();
        expect(pickListUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
