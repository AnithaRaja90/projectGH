/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { ProjectGhTestModule } from '../../../test.module';
import { GodownStockDetailsComponent } from 'app/entities/godown-stock-details/godown-stock-details.component';
import { GodownStockDetailsService } from 'app/entities/godown-stock-details/godown-stock-details.service';
import { GodownStockDetails } from 'app/shared/model/godown-stock-details.model';

describe('Component Tests', () => {
    describe('GodownStockDetails Management Component', () => {
        let comp: GodownStockDetailsComponent;
        let fixture: ComponentFixture<GodownStockDetailsComponent>;
        let service: GodownStockDetailsService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ProjectGhTestModule],
                declarations: [GodownStockDetailsComponent],
                providers: []
            })
                .overrideTemplate(GodownStockDetailsComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(GodownStockDetailsComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GodownStockDetailsService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new GodownStockDetails(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.godownStockDetails[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
