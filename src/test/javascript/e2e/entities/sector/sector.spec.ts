import { browser, protractor } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { SectorComponentsPage, SectorUpdatePage } from './sector.page-object';

describe('Sector e2e test', () => {
    let navBarPage: NavBarPage;
    let sectorUpdatePage: SectorUpdatePage;
    let sectorComponentsPage: SectorComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Sectors', () => {
        navBarPage.goToEntity('sector');
        sectorComponentsPage = new SectorComponentsPage();
        expect(sectorComponentsPage.getTitle()).toMatch(/projectGhApp.sector.home.title/);
    });

    it('should load create Sector page', () => {
        sectorComponentsPage.clickOnCreateButton();
        sectorUpdatePage = new SectorUpdatePage();
        expect(sectorUpdatePage.getPageTitle()).toMatch(/projectGhApp.sector.home.createOrEditLabel/);
        sectorUpdatePage.cancel();
    });

    it('should create and save Sectors', () => {
        sectorComponentsPage.clickOnCreateButton();
        sectorUpdatePage.setSectorNameInput('sectorName');
        expect(sectorUpdatePage.getSectorNameInput()).toMatch('sectorName');
        sectorUpdatePage.setSectorAddressInput('sectorAddress');
        expect(sectorUpdatePage.getSectorAddressInput()).toMatch('sectorAddress');
        sectorUpdatePage.setSectorInchargeInput('sectorIncharge');
        expect(sectorUpdatePage.getSectorInchargeInput()).toMatch('sectorIncharge');
        sectorUpdatePage.setStatusInput('5');
        expect(sectorUpdatePage.getStatusInput()).toMatch('5');
        sectorUpdatePage.setCreatedByInput('5');
        expect(sectorUpdatePage.getCreatedByInput()).toMatch('5');
        sectorUpdatePage.setModifiedByInput('5');
        expect(sectorUpdatePage.getModifiedByInput()).toMatch('5');
        sectorUpdatePage.setCreatedAtInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
        expect(sectorUpdatePage.getCreatedAtInput()).toContain('2001-01-01T02:30');
        sectorUpdatePage.setUpdatedAtInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
        expect(sectorUpdatePage.getUpdatedAtInput()).toContain('2001-01-01T02:30');
        sectorUpdatePage.zonalSelectLastOption();
        sectorUpdatePage.save();
        expect(sectorUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
