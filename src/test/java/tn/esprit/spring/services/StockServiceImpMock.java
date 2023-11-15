package tn.esprit.spring.services;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Product;
//import tn.esprit.spring.devops_project.entities.Product;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.services.StockServiceImpl;
import tn.esprit.spring.services.StockServiceImpMock;


import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@Slf4j
 class StockServiceImpMock {

    @InjectMocks
    StockServiceImpl stockServices;

    @Mock
    StockRepository stockRepository;

    // @Mock
    // private ISubscriptionRepository subscriptionRepository;

    @Test
     void testAddStock() {
        Stock stock=new Stock();
        Set<Product> products = new HashSet<>();
        stock.setIdStock(2L);
        stock.setTitle("ttrproduct");
        stock.setProducts(products);


        // Mock the repository's save method
        when(stockRepository.save(stock)).thenReturn(stock);

        Stock result = stockServices.addStock(stock);

        // Verify that the save method was called once
        verify(stockRepository, times(1)).save(stock);

        // Verify that the result is not null
        assertNotNull(result);

        // Verify that the result is the same as the input skier
        assertEquals(stock, result);
    }




    @Test
     void testRetrieveAllStocks() {
        Set<Product> products = new HashSet<>();
        // Create a list of Skier objects using the parameterized constructor
        List<Stock>stockList=new ArrayList<Stock>();
        stockList.add(new Stock(2L,"stock2",products));
        stockList.add(new Stock(3L,"stock3",products));

        // Mock the behavior of the skierRepository to return the list of skiers
        when(stockRepository.findAll()).thenReturn(stockList);

        List<Stock> result = stockServices.retrieveAllStock();

        // Verify that the service method returns the expected result
        assertEquals(stockList, result);

    }

    @Test
     void testRetrieveSkier() {
        Set<Product> products = new HashSet<>();
        Stock stock=new Stock(1L,"Stock1",products);


        // Mock the behavior of the skierRepository to return the sampleSkier when findById is called with skierId
        when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));

        // Call the retrieveSkier function
        Stock retrievedStock = stockServices.retrieveStock(1L);

        // Verify that the retrieved skier is the same as the sampleSkier
        assertEquals(stock, retrievedStock);

        // Verify that the retrievedSkier's ID matches the skierId
        assertEquals(1L, retrievedStock.getIdStock());
    }


}