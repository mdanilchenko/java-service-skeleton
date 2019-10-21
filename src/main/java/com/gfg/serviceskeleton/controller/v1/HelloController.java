package com.gfg.serviceskeleton.controller.v1;

import com.gfg.serviceskeleton.dto.v1.HelloDTO;
import com.gfg.serviceskeleton.entity.Hello;
import com.gfg.serviceskeleton.exception.ResourceNotFoundException;
import com.gfg.serviceskeleton.service.HelloService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/hello")
@Api(value = "Hello")
public class HelloController {

    private HelloService helloService;

    private static final ModelMapper mapper = new ModelMapper();

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @ApiOperation(value = "Get entry by UUID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Processed successfully", response = HelloDTO.class),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HelloDTO> getByUuid(@PathVariable String uuid) {
        Hello hello = helloService.getByUuid(uuid);
        if (hello == null) {
            throw new ResourceNotFoundException("Hello", "UUID", uuid);
    }

        return new ResponseEntity<>(mapper.map(hello, HelloDTO.class), HttpStatus.OK);
    }

    @ApiOperation(value = "View all entries")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Processed successfully", response = HelloDTO.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<HelloDTO>> getAll() {
        List<Hello> hellos = helloService.getAll();

        List<HelloDTO> helloDTOList = hellos.stream()
                .map(hello -> mapper.map(hello, HelloDTO.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(helloDTOList, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete entry by UUID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Processed successfully", response = Object.class),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @DeleteMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable String uuid) {
        Hello hello = helloService.getByUuid(uuid);
        if (hello == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        helloService.delete(hello);

        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Update entry by UUID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Processed successfully", response = HelloDTO.class),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found.")
    })
    @PutMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HelloDTO> update(@PathVariable String uuid, @Valid @RequestBody HelloDTO helloDTO) {
        Hello hello = mapper.map(helloDTO, Hello.class);

        return new ResponseEntity<>(
                mapper.map(
                        helloService.updateByUuid(uuid, hello),
                        HelloDTO.class),
                HttpStatus.OK
        );
    }

    @ApiOperation(value = "Create a new entry")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Processed successfully", response = HelloDTO.class),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@Valid @RequestBody HelloDTO helloDTO) {
        Hello hello = mapper.map(helloDTO, Hello.class);
        helloService.save(hello);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
