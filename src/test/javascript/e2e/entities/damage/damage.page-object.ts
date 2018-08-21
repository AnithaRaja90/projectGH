import { element, by, promise, ElementFinder } from 'protractor';

export class DamageComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-damage div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class DamageUpdatePage {
    pageTitle = element(by.id('jhi-damage-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    noOfQuantityInput = element(by.id('field_noOfQuantity'));
    dateInput = element(by.id('field_date'));
    statusInput = element(by.id('field_status'));
    createdByInput = element(by.id('field_createdBy'));
    modifiedByInput = element(by.id('field_modifiedBy'));
    createdAtInput = element(by.id('field_createdAt'));
    updatedAtInput = element(by.id('field_updatedAt'));
    batchSelect = element(by.id('field_batch'));
    descriptionSelect = element(by.id('field_description'));

    getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    setNoOfQuantityInput(noOfQuantity): promise.Promise<void> {
        return this.noOfQuantityInput.sendKeys(noOfQuantity);
    }

    getNoOfQuantityInput() {
        return this.noOfQuantityInput.getAttribute('value');
    }

    setDateInput(date): promise.Promise<void> {
        return this.dateInput.sendKeys(date);
    }

    getDateInput() {
        return this.dateInput.getAttribute('value');
    }

    setStatusInput(status): promise.Promise<void> {
        return this.statusInput.sendKeys(status);
    }

    getStatusInput() {
        return this.statusInput.getAttribute('value');
    }

    setCreatedByInput(createdBy): promise.Promise<void> {
        return this.createdByInput.sendKeys(createdBy);
    }

    getCreatedByInput() {
        return this.createdByInput.getAttribute('value');
    }

    setModifiedByInput(modifiedBy): promise.Promise<void> {
        return this.modifiedByInput.sendKeys(modifiedBy);
    }

    getModifiedByInput() {
        return this.modifiedByInput.getAttribute('value');
    }

    setCreatedAtInput(createdAt): promise.Promise<void> {
        return this.createdAtInput.sendKeys(createdAt);
    }

    getCreatedAtInput() {
        return this.createdAtInput.getAttribute('value');
    }

    setUpdatedAtInput(updatedAt): promise.Promise<void> {
        return this.updatedAtInput.sendKeys(updatedAt);
    }

    getUpdatedAtInput() {
        return this.updatedAtInput.getAttribute('value');
    }

    batchSelectLastOption(): promise.Promise<void> {
        return this.batchSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    batchSelectOption(option): promise.Promise<void> {
        return this.batchSelect.sendKeys(option);
    }

    getBatchSelect(): ElementFinder {
        return this.batchSelect;
    }

    getBatchSelectedOption() {
        return this.batchSelect.element(by.css('option:checked')).getText();
    }

    descriptionSelectLastOption(): promise.Promise<void> {
        return this.descriptionSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    descriptionSelectOption(option): promise.Promise<void> {
        return this.descriptionSelect.sendKeys(option);
    }

    getDescriptionSelect(): ElementFinder {
        return this.descriptionSelect;
    }

    getDescriptionSelectedOption() {
        return this.descriptionSelect.element(by.css('option:checked')).getText();
    }

    save(): promise.Promise<void> {
        return this.saveButton.click();
    }

    cancel(): promise.Promise<void> {
        return this.cancelButton.click();
    }

    getSaveButton(): ElementFinder {
        return this.saveButton;
    }
}
