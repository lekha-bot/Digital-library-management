package com.example.librarysystem.service;

import com.example.librarysystem.entity.Resource;
import com.example.librarysystem.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    // Add resource
    public Resource addResource(Resource resource) {
        return resourceRepository.save(resource);
    }

    // Get all resources
    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }

    // Get resources by book
    public List<Resource> getResourcesByBook(Long bookId) {
        return resourceRepository.findByBookId(bookId);
    }

    // Delete resource
    public void deleteResource(Long id) {
        resourceRepository.deleteById(id);
    }
}
